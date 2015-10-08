function DataStream() {
  var randomGenerator = function() {
    return Math.floor(Math.random() * 100 + 1);
  }
  return {
    getMeta: function() {},
    getLastSnapshot: function() {},
    getDataStream: function(evtSource) {
      var responseStream = Rx.Observable.create(function(observer) {
        /*        var cnt = 0;
                setInterval(function() {
                  cnt++;
                  var dataSnapshotSample = {
                    timestamp: new Date(),
                    columns: ['Toronto', 'New York', 'Kiev'],
                    data: [
                      [randomGenerator(), randomGenerator(), randomGenerator()],
                      [0, 2, 0]
                    ]
                  };
                  observer.onNext(dataSnapshotSample);
                }, 2000);*/

        // Get events from the server
        evtSource.onmessage = function(e) {
          console.log("message: " + e.data);
          observer.onNext(e.data);
        }

      });
      return responseStream;
    }
  }
}

var metaDataSample = {
  columnsMap: {
    'Toronto': 0,
    'New York': 1,
    'Kiev': 2
  }
};

var dataSnapshotSample = {
  timestamp: new Date(),
  columns: ['Toronto', 'New York', 'Kiev'],
  data: [
    [10, 20, 30],
    [0, 2, 0]
  ]
};


// Usage sample

function usageSample() {
  var dataStream = new DataStream();
  // Replace the URL to our Server URl
  var evtSource = new EventSource("sse");
  var stream = dataStream.getDataStream(evtSource);
  stream.subscribe(function(dataSnapshot) {
    console.log("dataSnapshot: ");
    console.log(dataSnapshot);
  });
}

usageSample();