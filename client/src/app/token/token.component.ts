import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-token',
    templateUrl: './token.component.html',
    styleUrls: ['./token.component.scss']
})
export class TokenComponent implements OnInit {

    private tokens: string[] = ["token1", "token2", "token3", "token4", "token5"];
    private number: number[] = [1, 1, 1, 1, 1];

    constructor() { }

    ngOnInit() {
    }

    subtract(index) {
        if (this.number[index] > 1) this.number[index]--;
    }

    add(index) {
        this.number[index]++;
    }

}
