import { Component, OnInit } from '@angular/core';

import { DropdownObject } from '../interfaces/common.interfaces';

@Component({
    selector: 'app-algorithm-dropdown',
    templateUrl: './algorithm-dropdown.component.html',
    styleUrls: ['./algorithm-dropdown.component.scss']
})
export class AlgorithmDropdownComponent implements OnInit {

    private algorithms: DropdownObject[] = [
        { name: 'TF', code: 'TF' },
        { name: 'TF boost title', code: 'TF_BOOST_TITLE' },
        { name: 'TF squared', code: 'TF_SQUARED' },
        { name: 'TF-IDF', code: 'TF_IDF' }
    ]
    private currentAlgorithm;

    constructor() { }

    ngOnInit() {
        this.currentAlgorithm = this.algorithms[0].code;
    }
}
