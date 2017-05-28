import { Component, ViewChild } from '@angular/core';

import { AlgorithmDropdownComponent } from './algorithm-dropdown/algorithm-dropdown.component';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    private appName: string = "MedSort";
    currentAlgorithm: string;

    @ViewChild('algorithmDropdown') algorithmDropdown: AlgorithmDropdownComponent;

    ngAfterViewInit() {
        this.setCurrentAlgorithm();
    }

    setCurrentAlgorithm() {
        this.currentAlgorithm = this.algorithmDropdown.getCurrentAlgorithm();
    }

}