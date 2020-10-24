package com.techelevator;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.ReservationDAO;
import com.techelevator.excelsior.model.Space;
import com.techelevator.excelsior.model.SpaceDAO;
import com.techelevator.excelsior.model.Venue;
import com.techelevator.excelsior.model.VenueDAO;
import com.techelevator.excelsior.model.JDBC.JDBCReservationDAO;
import com.techelevator.excelsior.model.JDBC.JDBCSpaceDao;
import com.techelevator.excelsior.model.JDBC.JDBCVenueDAO;
import com.techelevator.view.Menu;

import java.sql.Date;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ExcelsiorCLI {

	private static final String MAIN_MENU_OPTION_LIST_VENUES = "List Venues";
	private static final String MAIN_MENU_OPTION_QUIT = "Quit";

	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_LIST_VENUES,
			MAIN_MENU_OPTION_QUIT };
	private static final String MENU_OPTION_RETURN_TO_PREVIOUS_SCREEN = "Return to previous screen";

	private static final String SPACES_MENU_VIEW_SPACES = "View Spaces";
	private static final String MENU_OPTION_SEARCH_RES_IF_AVAILABLE = "Search for Available Spaces";
	private static final String MENU_OPTION_SEE_RESERVATIONS_FOR_VENUE = "See Reservations at this Venue";
	private static final String[] SECOND_MENU_OPTIONS = new String[] { SPACES_MENU_VIEW_SPACES,
			MENU_OPTION_SEARCH_RES_IF_AVAILABLE, MENU_OPTION_SEE_RESERVATIONS_FOR_VENUE, MENU_OPTION_RETURN_TO_PREVIOUS_SCREEN };

	private static final String MAIN_MENU_QUESTION = "What would you like to do?";
	private static final String WHEN_DO_YOU_NEED_THE_SPACE = "When do you need the space?";
	private static final String HOW_MANY_DAYS = "How many days will you need the space?";
	private static final String HOW_MANY_PEOPLE = "How many people will be in attendance?";
	private static final String WHICH_VENUE = "Which venue would you like to view?";
	private static final String WHAT_DO_NEXT = "What would you like to do next?";

	private SpaceDAO spaceDAO;
	private VenueDAO venueDAO;
	private ReservationDAO reservationDAO;
	private boolean keepGoing = true;
	private Menu menu;
	private boolean noReservation;
	
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}

	public ExcelsiorCLI(DataSource datasource) {
		spaceDAO = new JDBCSpaceDao(datasource);
		venueDAO = new JDBCVenueDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
		menu = new Menu(System.in, System.out);
	}

	public void run() {
		while (keepGoing) {
			printHeading(MAIN_MENU_QUESTION);
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_LIST_VENUES)) {
				noReservation = true;
				listVenues();
				
			} else if (choice.equals(MAIN_MENU_OPTION_QUIT)) {
				quit();
			}
		}
	}

	private void listVenues() {
		while (noReservation) {
			printHeading(WHICH_VENUE);
			List<Venue> venues = venueDAO.selectAllVenues();
			String[] venueNameArray = new String[venues.size() + 1];
			int i = 0;
			for (Venue venue : venues) {
				venueNameArray[i] = venue.getName();
				i++;
			}
			venueNameArray[i] = MENU_OPTION_RETURN_TO_PREVIOUS_SCREEN;
			Object choice = menu.getChoiceFromOptions(venueNameArray);
			if (choice.equals(MENU_OPTION_RETURN_TO_PREVIOUS_SCREEN)) {
				break;
			}
			selectVenue(choice.toString());
		}
	}

	private void displayVenueOptions(Venue venue) {
		
		while (noReservation) {
			printHeading(WHAT_DO_NEXT);
			Object choice = menu.getChoiceFromOptions(SECOND_MENU_OPTIONS);
			if (choice.equals(SPACES_MENU_VIEW_SPACES)) {
				getVenueSpaces(venue);
			} else if (choice.equals(MENU_OPTION_SEARCH_RES_IF_AVAILABLE)) {
				searchForReservation(venue);
			} else if (choice.equals(MENU_OPTION_SEE_RESERVATIONS_FOR_VENUE)){
				printReservationsInTimePeriod(reservationDAO.getReservationsInNext30ByVenueId(venue.getVenueId()));
			}else if (choice.equals(MENU_OPTION_RETURN_TO_PREVIOUS_SCREEN)) {
				return;
			}
				
			
		}
	}

	private void selectVenue(String choice) {
		
		while (noReservation) {
			List<Venue> venues = venueDAO.searchVenueByName(choice);
			Venue venue = new Venue();
			for (Venue venueIn : venues) {
				venue = venueIn;
			}
			printVenue(venue);
			return;
		}
	}

	private void getVenueSpaces(Venue venue) {
		List<Space> spaces = spaceDAO.listSpacesByVenueId(venue.getVenueId());
		printSpaces(spaces, venue);

	}

	private void searchForReservation(Venue venue) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate date = null;
		while (date == null) {
			try {
				date = LocalDate.parse(getUserInput(WHEN_DO_YOU_NEED_THE_SPACE), formatter);

			} catch (DateTimeParseException e) {
				System.out.println("Please enter date in this format - MM-dd-yyyy");
			}
			if (date.isBefore(LocalDate.now())) {
				System.out.println("That date has passed.");
				date = null;
			}
		}
		int numberOfDays = 0;
		while (numberOfDays == 0) {
		
			numberOfDays = tryParseInt(getUserInput(HOW_MANY_DAYS));
			if (numberOfDays < 1) {
				System.out.println("You need to book at least one day!");
				numberOfDays = 0;
			
			}
		}
		
		int numberOfPeople = 0;
		while(numberOfPeople == 0) {
				numberOfPeople = tryParseInt(getUserInput(HOW_MANY_PEOPLE));
				if (numberOfPeople < 1) {
					System.out.println("You need at least one person!");
					numberOfPeople = 0;
				
				}
		}
		List<Space> spaces = spaceDAO.getSpacesIfAvailable(venue.getVenueId(), date, numberOfDays, numberOfPeople);
		printAvailableSpaces(spaces, venue, numberOfDays, date);
		
		
	}

	private void quit() {
		keepGoing = false;
	}

	private void printSpaces(List<Space> spaces, Venue venue) {
		printHeading(venue.getName() + " Spaces");
		String spaceFormat = "%-3s %-26s %-10s %-10s %10s %15s";
		printHeading(String.format(spaceFormat, "", "Name", "Open", "Close", "Daily Rate", "Max Occupancy"));
		int num = 1;
		for (Space space : spaces) {

			System.out.println(String.format(spaceFormat, "#" + Integer.toString(num), space.getName(),
					formatMonth(space.getOpenFrom()) , formatMonth(space.getOpenTo()) ,
					"$" + String.format("%.2f", space.getDailyRate()), Integer.toString(space.getMaxOccupancy())));
			num++;
		}
	}

	
	private void printReservationsInTimePeriod(List<Reservation> reservations) {
		
		if(reservations.size() < 1) {
			System.out.println("There are no reservations at this venue in the next 30 days.");
			
		} else {
			String formatter = "%-26s %-20s %-20s %-12s %-12s";
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
			printHeading("The following reservations are coming up in the next 30 days");
			System.out.println(String.format(formatter,"Venue", "Space", "Reserved For", "From", "To"));
			
			System.out.println();
			for(Reservation reservation : reservations) {
				String venueName = venueDAO.getVenueById(spaceDAO.getSpaceById(reservation.getSpaceId()).getVenueId()).getName();
				String spaceName = spaceDAO.getSpaceById(reservation.getSpaceId()).getName();
				System.out.println(String.format(formatter, venueName, spaceName, 
						reservation.getReservedFor(), 
						reservation.getStartDate().format(dateTimeFormatter).toString(),
						reservation.getEndDate().format(dateTimeFormatter).toString()));	
				
				
			}
		}
		
	}
	
	private void printAvailableSpaces(List<Space> spaces, Venue venue, int numberOfDays, LocalDate date) {
		List<Space> spacesAvailable = new ArrayList<Space>();
		if (spaces.size() > 0) {
			printHeading("Please select from the following spaces ");
			String spaceFormat = "%-5s %-26s %15s %16s %12s %12s";
			printHeading(String.format(spaceFormat, "Space#", "Name", "Daily Rate", "Max Occupancy",  "Accessible?", "Total Cost"));
			
			for (Space space : spaces) {
				System.out.println(String.format(spaceFormat,
						"#" + Integer.toString(space.getSpaceId()), 
						space.getName(),
						"$" + String.format("%.2f", space.getDailyRate()),
						Integer.toString(space.getMaxOccupancy()),
						Boolean.toString(space.getIsAccessible()),
						"$" + String.format("%.2f", space.getDailyRate()* numberOfDays)));
				spacesAvailable.add(space);
			}
		} else if (spaces.size() < 1){
			System.out.println("That date is not available.");
			return;
		}
		
		reserveSpace(spacesAvailable, numberOfDays, date, numberOfDays);
	}
	
	private void reserveSpace(List<Space> spaces, int numberOfDays, LocalDate date, int numberOfPeople) {
		int choiceInt = 0;
		while (choiceInt == 0) {
			System.out.println();
			String choice = getUserInput("Which space would you like to reserve (enter 0 to cancel) ?");

			try {
				choiceInt = Integer.parseInt(choice);
			} catch (NumberFormatException e) {
				System.out.println("Invalid space entry. Please enter a space number.");
			}
			if (choiceInt == 0) {
				return;
			}
			for (Space space : spaces) {
				if (space.getSpaceId() == choiceInt) {

					String reservedFor = getUserInput("Who is the space being reserved for?");
					int reservationId = reservationDAO.makeAReservation(space.getSpaceId(), date, numberOfDays, numberOfPeople,
							reservedFor);
					printReservationDetails(reservationId, space, numberOfDays);
					return;
				}
			}
			System.out.println("Invalid space number.");
		}

	}
	
	private void printReservationDetails(int reservationId, Space space, int numberOfDays) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		Reservation reservation = reservationDAO.getReservationById(reservationId);
		printHeading("Thanks for submitting your reservation! The details for your event are listed below:");
		System.out.println("Confirmation #: " + reservation.getResId());
		System.out.println("Venue: " + venueDAO.getVenueById(space.getVenueId()).getName());
		System.out.println("Space: " + space.getName());
		System.out.println("Reserved For: " + reservation.getReservedFor());
		System.out.println("Attendees: " + reservation.getNumberOfAttendees());
		System.out.println("Arrival Date: " + reservation.getStartDate().format(dateTimeFormatter));
		System.out.println("Depart Date: " + reservation.getEndDate().format(dateTimeFormatter));
		System.out.println("Total Cost: $" + (int) space.getDailyRate() * numberOfDays);
		noReservation = false;
		
	}
	
	private void printVenue(Venue venue) {
		
		printHeading(venue.getName());
		System.out.println("Location: " + venue.getCity() + ", " + venue.getState());
		System.out.println("Categories: " + venue.getCategories());
		System.out.println();
		System.out.println(venue.getDescription());
		displayVenueOptions(venue);

	}

	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private int tryParseInt(String string) {
		int integer = 0;
		try {
			integer = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			System.out.println("Not a valid number.");
		}
		return integer;
	}
	
	private String formatMonth(int month) {
		if (month == 0) {
			return "";
		}
		return new DateFormatSymbols().getShortMonths()[month - 1] + ".";
	}

	@SuppressWarnings("resource")
	private String getUserInput(String prompt) {
		System.out.print(prompt + " >>> ");
		return new Scanner(System.in).nextLine();
	}

}
