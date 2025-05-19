'use client'
import GridCard from "@/components/GridCard";
import { Col, Row } from "react-bootstrap";
import ModalBox from "@/components/modal/ModalBox";
import { NextUIBase } from "@/NextUIBase";
import { useRef, useState } from "react";
import { RegularButton } from "../next-components/buttons/RegularButton";
import { FormGenText } from "@/components/formGenComponents/FormGenText";
interface Props{
    boards:Array<Object>;
    addForm:Object;
}
export default function Client(props:any){
    const modalRef:any=useRef(null);
    const uiBase=new NextUIBase();
    const [warning,setWarning]=useState({
        name:''
    });
    const click=()=>{
        modalRef.current.open();
    }
    const submit=async()=>{
       uiBase.onChange('name','test',setWarning,warning);
    }
    const addHandle=(event:any)=>{
        console.log(event);
        //modalRef.current.open();
    }
    console.log(props);
    return(
        <div>
        <Row>
        <Col md={2} xs={2}></Col>
        <Col md={10} xs={14}>
        <div>
        <button onClick={click}>test</button>
        <GridCard valueKey={"id"} data={[{id:1,name:'test'},{id:2,name:'test'}]} titleKey={"name"} bodyKey={""} enableLink={true} linkUrl={'/boards/'} onClick={addHandle} />
        </div>
        </Col>
        <Col>
        </Col>   
        </Row>
        <ModalBox ref={modalRef} title={"Add Board"} submitCaption="Add" submit={submit}>
        <div>
        <Row>
        <Col md={7} xs={9}>
        <FormGenText label={"Name"} type={""} rows={0} value={""} warning={warning.name}/>
        <FormGenText label={"Password"} type={"password"} rows={0} value={""}/>
        </Col>
        </Row>
        </div>  
        </ModalBox>
        </div>
    );
}