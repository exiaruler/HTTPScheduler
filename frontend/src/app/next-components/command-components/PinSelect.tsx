'use client'
import FormGenSelect from "@/components/formGenComponents/FormGenSelect";
import { ChangeEventHandler } from "react";
type Props={
    label:string,
    name?:string,
    valueKey:string,
    displayKey:string,
    options?:any,
    api?:string,
    required?:boolean,
    readOnly?:boolean,
    disable?:boolean,
    onChange?:any,
    warning?:string,
    value:any,
    size?:any,
    md?:number,
    xs?:number,
    pinIndex:number
}
export default class PinSelect extends FormGenSelect{

}