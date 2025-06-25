'use client'
import { Component, ReactNode } from "react";
interface Props{
    children?:ReactNode;
    onSubmit?:any;
    recordLayout:Object;
}
interface State{
    recordLayout:Object
}
export default class FormHandle extends Component<Props,State>{
    constructor(props:Props) {
            super(props);
            this.state = {
                recordLayout:{}
            };
    }
    componentDidMount(): void {
        if(this.props.recordLayout){
            this.setState({...this.state,recordLayout:this.props.recordLayout});
        }
    }
    onsubmit(event:any){
        event.preventDefault();
        if(this.props.onSubmit){
            this.props.onSubmit();
        }

    }
    render(){
        return(
            <form onSubmit={(event:any)=>this.onsubmit(event)}>
            {
                this.props.children
            }
            </form>
        )
    }
}