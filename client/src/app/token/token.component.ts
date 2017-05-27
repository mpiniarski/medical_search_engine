import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'app-token',
    templateUrl: './token.component.html',
    styleUrls: ['./token.component.scss']
})
export class TokenComponent implements OnInit {

    private number: number[] = [1, 1, 1, 1, 1];

    @Input() tokens;

    constructor() { }

    ngOnInit() { }

    subtract(index) {
        if (this.number[index] > 1) this.number[index]--;
    }

    add(index) {
        this.number[index]++;
    }

}
