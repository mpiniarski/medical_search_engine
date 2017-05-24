import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MdlModule } from 'angular2-mdl';

import { AppComponent } from './app.component';
import { EllipsisPipe } from './ellipsis.pipe';

@NgModule({
    declarations: [
        AppComponent,
        EllipsisPipe
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        MdlModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
