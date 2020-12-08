import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'pricepipe'
})
export class PricepipePipe implements PipeTransform {

  transform(value: any, count:any): any {
    return value*count;
  }

}
