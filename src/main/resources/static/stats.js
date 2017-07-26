function getStats() {
    $.ajax({
        type: 'GET',
        url: 'stats/getStatsPerSecond',
        // headers: {
        //     'Accept': 'application/json',
        //     'Content-Type': 'application/json'
        // },
        // data: {
        //     messageText: messageText
        // },
        success: function (data) {
            //var data1 = 'ssss';
            $('#statsValuePerSecond').val(data);
            //$('#inputGetStats').val(data1);
        }
    });

}

// $(document).ready(function () {
//     getStats();
// });