'use client'
import ModalBox from "@/components/modal/ModalBox"
import { RegularButton } from "@/app/next-components/buttons/RegularButton"
import { FormGenText } from "@/components/formGenComponents/FormGenText"
import TabComponent from "@/components/Tab/TabComponent"
import TabGroup from "@/components/Tab/TabGroup"
import { Row, Col } from "react-bootstrap"
import { useEffect, useRef, useState } from "react"
import DeleteBox from "@/components/modal/DeleteBox"
import { Board } from "@/interface/boardInterface"
import { Hardware } from "@/interface/hardwareInterface"
import { NextUIBase } from "@/NextUIBase"
import FormComponent from "@/components/formGenComponents/layout/formComponent"
import FormLayout from "@/components/formGenComponents/layout/formComponent"
import { useRouter } from 'next/navigation';

export default function Client(props:any){
    const passwordModalRef:any=useRef(null);
    const deleteModalRef:any=useRef(null);
    const [activated,setActivated]=useState(true);
    const [board,setBoard]:Board=useState(null);
    const [hardware,setHardware]:Hardware=useState(null);
    const router = useRouter();
    const uiBase=new NextUIBase();
    const openChangePass=()=>{
        passwordModalRef.current.open();
    }
    const openDelete=()=>{
        deleteModalRef.current.open();
    }
    const loadBoard=(board:Board)=>{
        setActivated(board.activated);
        setBoard(board);
    }
    const loadHardware=(hardware:Hardware)=>{
        setHardware(hardware);
    }
    const status=(bool:boolean)=>{
        var show="Inactive";
        if(!bool) show="Active";
        return show;
    }
    const deletehandle=()=>{
        router.push('/boards');
    }
    useEffect(()=>{
        loadBoard(props.data.board.board);
        loadHardware(props.data.board.hardware);
        console.log(props.data);
    },[])
    return(
        <div>
        <Row>
        <Col md={2} xs={2}></Col>
        <Col md={10} xs={14}>
        <TabGroup defaultActiveKey={"board"}>
        <TabComponent title={board?.name} eventKey={"board"}>
        <div>
        <Row>
        <Col md={3} xs={9}>
        <FormGenText label={"Board ID"} type={""} rows={0} value={board?.boardId} readOnly={true}/>
        <FormGenText label={"Board Model"} type={""} rows={0} value={hardware?.boardName} readOnly={true}/>
        <FormGenText label={"Status"} type={""} rows={0} value={status(board?.activated)} readOnly={true}/>
        </Col>
        <Col md={3} xs={9}>
        <FormGenText label={"RAM Usage"} type={""} rows={0} value={board?.ramUsage} readOnly={true}/>
        <FormGenText label={"Total RAM"} type={""} rows={0} value={hardware?.maxRam} readOnly={true}/>
        <FormGenText label={"Local IP"} type={""} rows={0} value={board?.ip} readOnly={true}/>
        </Col>
        <Col md={3} xs={9}>
        <FormGenText label={"Routine Check"} type={""} rows={0} value={board?.periodicCheck} readOnly={true}/>
        </Col>
        </Row>
        <Row>
        <Col>
        <RegularButton caption={"Change Password"} onClick={openChangePass} size={undefined} type={undefined}/>
        <RegularButton caption={"Configurations"} size={undefined} type={undefined}/>
        <RegularButton caption={"Reset"} size={undefined} type={undefined}/>
        <RegularButton caption={"Delete Board"} size={undefined} type={undefined} onClick={openDelete}/>
        </Col>    
        </Row>
        </div>
        </TabComponent>
        {
            <TabComponent title={"Device 1"} eventKey={"1"} disabled={!activated}>
            <TabGroup defaultActiveKey={"overview"}>
            <TabComponent title={"Overview"} eventKey={"overview"}>
                
            </TabComponent>
            <TabComponent title={"Functions"} eventKey={"functions"}>
                
            </TabComponent>
            </TabGroup>
            </TabComponent>
        }
        <TabComponent title={"Add Device"} eventKey={"add"} disabled={!activated}>
        <FormLayout id={""} formId={""} valueKey={""} externalUrl={uiBase.util.baseUrlIo}/>
        </TabComponent>
        </TabGroup>
        </Col>
        </Row> 
        <ModalBox ref={passwordModalRef} title={"Password Change"}>
        </ModalBox>
        <DeleteBox ref={deleteModalRef} title={"Delete Board"} deleteApi={"/board/"} baseUrl={uiBase.util.baseURL+'/api'} param={board?.id} afterSubmit={deletehandle}>
        <p>Are you sure you want to delete {board?.name}?</p>
        <p>All devices and functionality associated will be lost</p>
        </DeleteBox>
        </div>
    )
}