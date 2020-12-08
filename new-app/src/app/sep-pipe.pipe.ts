import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sepPipe'
})
export class SepPipePipe implements PipeTransform {

  transform(value: string, count:number): any{
    return value.split(",")[count];
  }

}
