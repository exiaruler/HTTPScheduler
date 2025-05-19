'use client'
import ModalBox from "@/components/modal/ModalBox"
import { RegularButton } from "@/app/next-components/buttons/RegularButton"
import { FormGenText } from "@/components/formGenComponents/FormGenText"
import TabComponent from "@/components/Tab/TabComponent"
import TabGroup from "@/components/Tab/TabGroup"
import { Row, Col } from "react-bootstrap"
import { useRef, useState } from "react"

export default function Client(){
    const passwordModalRef:any=useRef(null);
    const [activated,setActivated]=useState(true);
    const openChangePass=()=>{
        passwordModalRef.current.open();
    }
    return(
        <div>
        <Row>
        <Col md={2} xs={2}></Col>
        <Col md={10} xs={14}>
        <TabGroup defaultActiveKey={"board"}>
        <TabComponent title={"Board Name"} eventKey={"board"}>
        <div>
        <Row>
        <Col md={3} xs={9}>
        <FormGenText label={"Board ID"} type={""} rows={0} value={""}/>
        <FormGenText label={"Board Model"} type={""} rows={0} value={""}/>
        <FormGenText label={"Status"} type={""} rows={0} value={""}/>
        <RegularButton caption={"Change Password"} onClick={openChangePass} size={undefined} type={undefined}/>
        <ModalBox ref={passwordModalRef} title={"Password Chnage"}/>
        </Col>
        </Row>
        </div>
        </TabComponent>
        {

        }
        <TabComponent title={"Add Device"} eventKey={"add"} disabled={!activated}>
            
        </TabComponent>
        </TabGroup>
        </Col>
        </Row>    
        </div>
    )
}