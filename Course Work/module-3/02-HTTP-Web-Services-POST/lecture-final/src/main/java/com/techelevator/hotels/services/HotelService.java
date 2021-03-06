package com.techelevator.hotels.services;

import com.techelevator.hotels.models.Hotel;
import com.techelevator.hotels.models.Reservation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class HotelService {

  private final String BASE_URL;
  private final RestTemplate restTemplate = new RestTemplate();
  private final ConsoleService console = new ConsoleService();

  public HotelService(String url) {
    BASE_URL = url;
  }

  /**
   * Create a new reservation in the hotel reservation system
   *
   * @param newReservation
   * @return Reservation
   */
  public Reservation addReservation(String newReservation) {
	  // Not part of the API, just to convert the user input to a Reservation
	  // object for this example
    Reservation reservation = makeReservation(newReservation);
    if (reservation == null) {
    	return null;
    }
    
    // 1) Create the HTTP Header for the request
    HttpHeaders headers = new HttpHeaders();
    // 2) Set the Content-Type Header to "application/json"
    headers.setContentType(MediaType.APPLICATION_JSON);
    // 3) Create HttpEntity Object that represents the Headers and the Body of the Request
    //    <Reservation> tells the HttpEntity that our message body will be serialized from our Reservation object
    //    The constructor arguments are:  ( object_to_build_message_body,  object_with_the_headers )
    HttpEntity<Reservation> requestEntity = new HttpEntity<Reservation>(reservation, headers);
    
    String url = BASE_URL + "hotels/" + reservation.getHotelID() + "/reservations";
    
    try {
    	// 4) use postForObject( url,  HttpEntity populated with the Header and Body Object, type to return)
    	reservation = restTemplate.postForObject(url, requestEntity, Reservation.class);
    } catch (RestClientResponseException e) {
		console.printError( e.getRawStatusCode() + " : " + e.getStatusText() );
	} catch (ResourceAccessException e) {
		console.printError( e.getMessage() ); 
	}
    
    return reservation;
  }

  /**
   * Updates an existing reservation by replacing the old one with a new
   * reservation
   *
   * @param CSV
   * @return
   */
  public Reservation updateReservation(String CSV) {
	  Reservation reservation = makeReservation(CSV);
	  if (reservation == null) {
    	return null;
	  }
	  
	  // 1) Create the Header
	  HttpHeaders headers = new HttpHeaders();
	  // 2) Set the Content-Type header to application/json
	  headers.setContentType(MediaType.APPLICATION_JSON);
	  // 3) Create a request entity and pass it the data object and header
	  HttpEntity<Reservation> requestEntity = new HttpEntity<Reservation>(reservation, headers);
	  
	  String url = "http://localhost:3000/reservations/" + reservation.getId();
	  
	  try {
		  restTemplate.put(url, requestEntity);
	  } catch (RestClientResponseException e) {
		  console.printError( e.getRawStatusCode() + " : " + e.getStatusText() );
	  } catch (ResourceAccessException e) {
		  console.printError( e.getMessage() ); 
	  }
	  
	  return reservation;
  }

  /**
   * Delete an existing reservation
   *
   * @param id
   */
  public void deleteReservation(int id) {
	  String url = BASE_URL + "reservations/" + id;
	  try {
		  restTemplate.delete(url);
	  } catch (RestClientResponseException e) {
		  console.printError( e.getRawStatusCode() + " : " + e.getStatusText() );
	  } catch (ResourceAccessException e) {
		  console.printError( e.getMessage() ); 
	  }
  }

 

  /**
   * List all hotels in the system
   *
   * @return
   */
  public Hotel[] listHotels() {
	Hotel[] hotels = null;
	try {
		hotels = restTemplate.getForObject(BASE_URL + "hotels", Hotel[].class);
	} catch (RestClientResponseException e) {
		console.printError( e.getRawStatusCode() + " : " + e.getStatusText() );
	} catch (ResourceAccessException e) {
		console.printError( e.getMessage() ); 
	}
    return hotels;
  }

  /**
   * Get the details for a single hotel by id
   *
   * @param id
   * @return Hotel
   */
  public Hotel getHotel(int id) {
	Hotel hotel = null;
	try {
		hotel = restTemplate.getForObject(BASE_URL + "hotels/" + id, Hotel.class);
	} catch (RestClientResponseException e) {
		// RestClientResponseException occurs for HttpStatus's above
		// 299, for example 401, 404, 500
		console.printError( e.getRawStatusCode() + " : " + e.getStatusText() );
	} catch (ResourceAccessException e) {
		// ResourceAccessException occurs then the connection cannot be made, 
		// for example the server is not available.
		console.printError( e.getMessage() ); 
	}
    return hotel;
  }

  /* DON'T MODIFY ANY METHODS BELOW */
  /**
   * List all reservations in the hotel reservation system
   *
   * @return Reservation[]
   */
  public Reservation[] listReservations() {
    Reservation[] reservations = null;
    try {
      reservations = restTemplate.getForObject(BASE_URL + "reservations", Reservation[].class);
    } catch (RestClientResponseException ex) {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return reservations;
  }

  /**
   * List all reservations by hotel id.
   *
   * @param hotelId
   * @return Reservation[]
   */
  public Reservation[] listReservationsByHotel(int hotelId) {
    Reservation[] reservations = null;
    try {
      reservations = restTemplate.getForObject(BASE_URL + "hotels/" + hotelId + "/reservations", Reservation[].class);
    } catch (RestClientResponseException ex) {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return reservations;
  }

  /**
   * Find a single reservation by the reservationId
   *
   * @param reservationId
   * @return Reservation
   */
  public Reservation getReservation(int reservationId) {
    Reservation reservation = null;
    try {
      reservation = restTemplate.getForObject(BASE_URL + "reservations/" + reservationId, Reservation.class);
    } catch (RestClientResponseException ex) {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return reservation;
  }

  private Reservation makeReservation(String CSV) {
    String[] parsed = CSV.split(",");

    // invalid input (
    if (parsed.length < 5 || parsed.length > 6) {
      return null;
    }

    // Add method does not include an id and only has 5 strings
    if (parsed.length == 5) {
      // Create a string version of the id and place into an array to be concatenated
      String[] withId = new String[6];
      String[] idArray = new String[] { new Random().nextInt(1000) + "" };
      // place the id into the first position of the data array
      System.arraycopy(idArray, 0, withId, 0, 1);
      System.arraycopy(parsed, 0, withId, 1, 5);
      parsed = withId;
    }

    return new Reservation(Integer.parseInt(parsed[0].trim()), Integer.parseInt(parsed[1].trim()), parsed[2].trim(),
        parsed[3].trim(), parsed[4].trim(), Integer.parseInt(parsed[5].trim()));
  }

}
