import CurrentInput from "./CurrentInput";
import ElectrodeSelect from "./ElectrodeSelect";
import PinSelect from "./PinSelect";
export interface Component{
    component:any,
    name:string
};
// function tab
const functionLibrary:Array<Component>=[
    {
        component:ElectrodeSelect,
        name:"ElectrodeSelect"
    },
    {
        component:PinSelect,
        name:"PinSelect"
    }
];
// mode tab
const modeLibrary:Array<Component>=[
    {
        component:CurrentInput,
        name:"OutputText"
    }
];
export function findComponentFunction(name:string){
    var  search=null;
    var find=functionLibrary.find(comp=>comp.name===name);
    if(find!=null&&find!=undefined) search=find;
    return search;
}
export function findComponentMode(name:string){
    var  search=null;
    var find=modeLibrary.find(comp=>comp.name===name);
    if(find!=null&&find!=undefined) search=find;
    return search;
}
