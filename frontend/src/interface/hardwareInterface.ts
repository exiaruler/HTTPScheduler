import { HardwarePin } from "./hardwarePinInterface";
export interface Hardware{
    id:number;
    createdDate:Date;
    updatedDate:Date;
    boardName:string;
    maxRam:number;
    pins:Array<HardwarePin>;
}