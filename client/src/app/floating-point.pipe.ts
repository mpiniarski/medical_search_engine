import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'floatingPoint'
})
export class FloatingPointPipe implements PipeTransform {

    transform(value: number, args?: any): any {
        const number = value.toString();

        if (args === undefined) {
            return value;
        }

        if (number.length > args) {
            return number.substring(0, args);
        } else {
            return number + '0'.repeat(args - number.length);
        }
    }

}
