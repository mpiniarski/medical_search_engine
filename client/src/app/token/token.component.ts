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

    subtract(index): void {
        if (this.number[index] > 1) this.number[index]--;
    }

    add(index): void {
        this.number[index]++;
    }

    getTokensWithWeights(): Object {
        let tokensMap: Object = {};
        for (var i in this.tokens) {
            tokensMap[this.tokens[i]] = this.number[i];
        }

        return tokensMap;
    }
}
