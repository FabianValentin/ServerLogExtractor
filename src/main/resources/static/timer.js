function startTimer() {
    let timerElement = document.getElementById('timer');
    let seconds = 0;

    function updateTimer() {
        seconds++;
        let hrs = Math.floor(seconds / 3600);
        let mins = Math.floor((seconds % 3600) / 60);
        let secs = seconds % 60;

        // Format time as HH:MM:SS
        let formattedTime =
            (hrs < 10 ? "0" : "") + hrs + ":" +
            (mins < 10 ? "0" : "") + mins + ":" +
            (secs < 10 ? "0" : "") + secs;

        timerElement.textContent = formattedTime;
    }

    // Update the timer every second
    setInterval(updateTimer, 1000);
}

// Start the timer when the page loads
window.onload = startTimer;