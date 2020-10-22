/*
 * Add Events and functions to move the Ship and Win the game
 */

document.addEventListener("DOMContentLoaded", () => {
  // Creates the Grid -- this should only occur after the DOM loads
  console.log("DOM loaded");
  createGrid();
});

/**
 * Reset the Game
 */
function resetGame() {

  const announce = document.querySelector('.announce');
  announce.innerText = "Play!";

  const frame = document.getElementById('frame');
  frame.firstElementChild.firstElementChild.classList.add('boat');
  frame.lastElementChild.lastElementChild.classList.add('treasure');
}



function getShipLocation() {
  const result = document.getElementById('frame').querySelector('.boat');
  return result;
}


/**
 * Creates the game grid
 */
function createGrid() {
  const frame = document.getElementById("frame");

  // Add Code to create the game grid
  for (let i = 0; i < 10; i++) {
    buildRow(frame);
  }

  resetGame();
}

/**
 * Builds the grid rows
 * @param {element} frame
 */
function buildRow(frame) {
  const row = document.createElement("div");
  row.classList.add("row");
  frame.insertAdjacentElement("beforeend", row);

  // Add code to create rows
  for (let i = 0; i < 10; i++) {
    buildSquare(row, i);
  }
}

/**
 * Builds the grid squares
 * @param {element} row
 * @param {int} count
 */
function buildSquare(row, count) {
  // Add code to create the grid Squares
  const container = document.createElement('div');
  container.classList.add('square');


  // Randomly creates pirates, but not on the first elemment
  if (count > 1  && count < 9) {
    if (rollDie(100) <= 15) {
      // Add the pirates here
      container.classList.add('pirate');
    }
  }

  row.insertAdjacentElement('beforeend',container);
}

/**
 * Rolls a fair die
 *
 * @param {int} sides
 * @returns {int} value in the range 1 <= x <= sides
 */
function rollDie(sides) {
  let result = Math.floor(Math.random() * sides) + 1;
  return result;
}

/**
 *Utiltiy function to demonstrate rollDie
 * Display to console the result of rolling the
 * die enought to get about 100 values from
 * each side of the die. User console to run
 * and see results.
 * Not called for this application
 *
 * @param {int} sides
 *
 */
function testRollDie(sides) {
  let result = [];
  for (let i = 0; i <= sides; i++) {
    result[i] = 0;
  }
  for (let i = 0; i < sides * 100; i++) {
    result[rollDie(sides)] += 1;
  }
  console.table(result);
}
