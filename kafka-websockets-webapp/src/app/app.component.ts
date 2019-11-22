import { Component } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import $ from 'jquery';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'kafka-websockets-webapp';
  private stompClient;

  constructor() {
    var myArray = ['8080', '8081', '8082'];
    var port = myArray[Math.floor(Math.random() * myArray.length)];
    console.log("Connected to port " + port);
    this.intitializeWebSocketConnection(port);
  }

  intitializeWebSocketConnection(port: string) {
    let ws = SockJS('http://localhost:' + port + '/socket');
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe("/topic/notification", (message) => {
        if (message.body) {
          $(".area").append("<div class='notification'>" + message.body + "</div>")
          console.log("Message received", message.body);
        }
      });
    });
  }

  sendMessage(message: string) {
    this.stompClient.send("/app/send/message", {}, message);
    console.log("Message sent", message)
    $('#inputtext').val('');
  }
}
