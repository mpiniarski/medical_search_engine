import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-algorithm-dropdown',
    templateUrl: './algorithm-dropdown.component.html',
    styleUrls: ['./algorithm-dropdown.component.scss']
})
export class AlgorithmDropdownComponent implements OnInit {

    private algorithms = ["TF", "TF-normalized", "TF-double title", "TF-IDF"];
    private currentAlgorithm: string;

    constructor() { }

    ngOnInit() {
        this.currentAlgorithm = this.algorithms[0];
    }

    getCurrentAlgorithm(): string {
        return this.currentAlgorithm;
    }

}
