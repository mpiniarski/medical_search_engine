import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'app-token',
    templateUrl: './token.component.html',
    styleUrls: ['./token.component.scss']
})
export class TokenComponent implements OnInit {

    numbers: number[];

    @Input() tokens;

    constructor() { }

    ngOnInit() { }

    private subtract(index): void {
        if (this.numbers[index] > 1) this.numbers[index]--;
    }

    private add(index): void {
        this.numbers[index]++;
    }

    public getTokensWithWeights(): Object {
        let tokensMap: Object = {};
        for (var i in this.tokens) {
            tokensMap[this.tokens[i]] = this.numbers[i];
        }

        return tokensMap;
    }

    public initTokensNumbers(length: number): void {
        this.numbers = Array(length).fill(1, 0, length);
    }

    public resetTokensSettings(): void {
        this.numbers = [];
    }

    public resetTokens(): void {
        this.tokens = [];
    }
}
