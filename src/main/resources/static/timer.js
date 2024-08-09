function startTimer(elementId, startTime) {
    let time = startTime;

    // Update the timer every second
    setInterval(function() {
        // Increment time by 1
        time++;

        // Calculate hours, minutes, and seconds
        let days = Math.floor(time / (60 * 60 * 24));
        let hours = Math.floor((time % (60 * 60 * 24)) / (60 * 60));
        let minutes = Math.floor((time % 3600) / 60);
        let seconds = time % 60;

        // Format the time string
        let formattedTime =
            (days + 'd ') +
            (hours < 10 ? '0' : '') + hours + ':' +
            (minutes < 10 ? '0' : '') + minutes + ':' +
            (seconds < 10 ? '0' : '') + seconds;

        // Display the formatted time
        document.getElementById(elementId).innerHTML = formattedTime;
    }, 1000); // 1000 milliseconds = 1 second
}

window.onload = function() {
    // Start timers with different initial times (in seconds)
    startTimer('timer1', 74522); // Starts from 0d 20:42:02
    startTimer('timer2', 1111);  // Starts from 0d 00:18:31
    startTimer('timer3', 90845);  // Starts from 1d 01:14:05
    startTimer('timer4', 868406);  // Starts from 10d 01:13:26
    startTimer('timer5', 1070766);  // Starts from 12d 09:26:06
    startTimer('timer6', 268406);  // Starts from 3d 02:33:26
    startTimer('timer7', 218657);  // Starts from 2d 12:44:17
    startTimer('timer8', 147779);  // Starts from 1d 17:02:59
    startTimer('timer9', 356243);  // Starts from 4d 02:57:23
    startTimer('timer10', 190994);  // Starts from 2d 05:03:14
};