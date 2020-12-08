import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'demopipe'
})
export class DemopipePipe implements PipeTransform {

  transform(value: any, products: any,count:any): any {
    for(var i=0;i<products.length;i++){
      value+=(products[i].price*count[i]);
    }
    return value;
    
  }

}
