function startTimer(elementId, startTime) {
    let time = startTime;

    // Update the timer every second
    setInterval(function() {
        // Increment time by 1
        time++;

        // Calculate hours, minutes, and seconds
        let hours = Math.floor(time / 3600);
        let minutes = Math.floor((time % 3600) / 60);
        let seconds = time % 60;

        // Format the time string
        let formattedTime =
            (hours < 10 ? '0' : '') + hours + ':' +
            (minutes < 10 ? '0' : '') + minutes + ':' +
            (seconds < 10 ? '0' : '') + seconds;

        // Display the formatted time
        document.getElementById(elementId).innerHTML = formattedTime;
    }, 1000); // 1000 milliseconds = 1 second
}

window.onload = function() {
    // Start timers with different initial times (in seconds)
    startTimer('timer1', 0);     // Starts from 00:00:00
    startTimer('timer2', 3600);  // Starts from 01:00:00
    startTimer('timer3', 5400);  // Starts from 01:30:00
};