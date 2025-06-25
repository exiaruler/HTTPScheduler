'use client'
import BackButton from "@/app/next-components/buttons/BackButton";
import { RegularButton } from "@/app/next-components/buttons/RegularButton";
import CurrentInput from "@/app/next-components/command-components/CurrentInput";
import ElectrodeSelect from "@/app/next-components/command-components/ElectrodeSelect";
import PinSelect from "@/app/next-components/command-components/PinSelect";
import FormHandle from "@/components/form/FormHandle";
import { FormGenCombo } from "@/components/formGenComponents/FormGenCombo";
import FormGenSelect from "@/components/formGenComponents/FormGenSelect";
import { FormGenText } from "@/components/formGenComponents/FormGenText";
import TabComponent from "@/components/Tab/TabComponent";
import TabGroup from "@/components/Tab/TabGroup";
import { NextUIBase } from "@/NextUIBase";
import { ReactNode, useEffect, useRef, useState } from "react";
import { Col, Row } from "react-bootstrap";
import { Component, findComponentFunction, findComponentMode } from "@/app/next-components/command-components/componentLibrary";
import React from "react";
import { useRouter } from 'next/navigation';
interface ComponentRender{
    component:any;
    config:any;
}
export default function Client({data}){
    const base=new NextUIBase();
    const router = useRouter();
    // mode json template
    const modeLayoutRef:any=useRef({});
    // form
    const [functionRecord,setFunctionRecord]=useState({});
    const [modeRecords,setModeRecords]=useState([]);
    const modeRecordsTaskRef:Array<Object>=useRef([]);
    // pins reference
    const pinsUsedRef=useRef([]);
    // command list
    const [commands,setCommands]:any=useState([]);
    // command selected
    const [command,setCommand]:any=useState(null);
    // board task
    const boardTask=useRef(null);
    // function form setup
    const [functionForm,setFunctionForm]:Array<any>=useState([]);
    // mode form layout
    const modeFormLay:any=useRef([]);
    const functionFormLay:any=useRef([]);
    // mode form setup
    const [modeForms,setModeForms]:any=useState([]);
    const functionFormRef:any=useRef([]);
    // mode tab display
    const [addMode,setAddMode]=useState(false);
    // hardware model
    const hardwareRef:any=useRef(null);
    // data references
    const boardRef:any=useRef(null);
    const deviceRef=useRef(null);
    // component ref
    const tabsRef:any=useRef(null);
    const modeTabsRef:any=useRef(null);
    const formRef:any=useRef(null);
   
    const tabHandle=(tab:any)=>{
        tabsRef.current.handleTabSwitch(tab);
    }
  
    const commandHandle=(event:any)=>{
        var value=event.target.value;
        if(value!=""){
            var commandJson=JSON.parse(value);
            const taskTempl=commandJson?.boardCommand;
            //taskTempl.id=0;
            if(commandJson?.commandParameter.length>0){
                clearCommandSelection();
                setAddMode(true);
                setCommand(commandJson);
                setFunctionRecord(prev=>({...prev,'command':commandJson}));
                var formParas=commandJson?.commandParameter;
                boardTask.current=taskTempl;
                // render fields for that command
                if(formParas.length>0){
                    var functionArr=[];
                    var modeArr=[];
                    var pinIndex=0;
                    // function tab
                    for(var i=0; i<formParas.length; i++){
                        var comp=findComponentFunction(formParas[i].component);
                        var compMode=findComponentMode(formParas[i].component);
                        var options=[];
                        var config:any={};
                        config.label=formParas[i].label;
                        const subkey=formParas[i].subKey;
                        if(comp!=null||comp!=undefined){
                            if(formParas[i].component=="ElectrodeSelect"){
                                options=data.electrodes;
                                config.options=options;
                                config.required=true;
                                config.name=formParas[i]?.backgroundKey;
                                config.onChange=(event:any)=>setFunctionRecord({...functionRecord,[event.target.name]:event.target.value});
                            }
                            if(formParas[i].component=="PinSelect"){
                                options=hardwareRef.current?.pins;
                                config.options=options;
                                config.displayKey="boardPin";
                                config.name=formParas[i]?.backgroundKey;
                                config.subName=subkey;
                                config.required=true;;
                                config.valueKey="pin";
                                const capturePin=pinIndex;
                                config.onChange=(event:any)=>pinOnChange(event.target.name,subkey,event.target.value,capturePin);
                                config.pinIndex=pinIndex;
                                pinIndex++;
                            }
                            const functionComp:ComponentRender={
                                component: comp.component,
                                config: config
                            };
                            functionArr.push(functionComp);
                        }
                        if(compMode!=null||compMode!=undefined){
                            var label=formParas[i].label;
                            var modeConfig:any={label:label,name:formParas[i]?.backgroundKey,index:0,onChange:null,subName:formParas[i]?.subKey,required:true};
                            const modeComp:ComponentRender={
                                component:compMode.component,
                                config:modeConfig
                            }
                            modeArr.push(modeComp);
                        }
                    }
                    //setFunctionForm(functionArr);
                    functionFormLay.current=functionArr;
                    modeFormLay.current=modeArr;
                    addModeForm(true);
                    
                }

            }else
            {

            }
            console.log(commandJson);
        }else
        {
            clearCommandSelection();
        }
    }

    const clearCommandSelection=()=>{
        setFunctionRecord({...functionRecord,command:null});
        setFunctionForm([]);
        setModeForms([]);
        setModeRecords([]);
        modeFormLay.current=[];
        setCommand(null);
        setAddMode(false);
        boardTask.current=null;
    }
    const renderFunctionForm=()=>{
        var layout:Array<ComponentRender>=functionFormLay.current;
        var formState=[];
        for(var i=0; i<layout.length; i++){
            formState.push(React.createElement(layout[i].component,layout[i].config));
        }
        setFunctionForm(formState);
    }
    const addModeForm=(rewrite:Boolean=false)=>{
        var modeTot=modeRecords.length;
        if(modeTot===0) {
            modeTot=1;
        }else modeTot++;
        var value="New Mode";
        var modeRec={...modeLayoutRef.current};
        const boardTsk={...boardTask.current};
        if(pinsUsedRef.current.length>0){
            boardTsk.pins=pinsUsedRef.current;
        }
        modeRec.boardAction=boardTsk;
        modeRec.mode=value;
        const task=boardTsk;
        var firstMode:Array<ReactNode>=[];
        var layout=modeFormLay.current;
        var currComp=null;
        let indexCount=0;
        for(var i=0; i<layout.length; i++){
            var lay=layout[i];
            if(lay.component!=currComp){
                currComp=lay.component;
                indexCount=0;
            }else indexCount++;
            const captureIndex=indexCount;
            lay.config.onChange=(event:any)=>commandOnChange(modeRecords.length,event.target.name,lay.config.subName,event.target.value,captureIndex);
            firstMode.push(React.createElement(lay.component,lay.config));
        }
        if(!rewrite){
            const existFormArr=[...modeForms];
            existFormArr.push(firstMode);
            setModeForms((prev:any)=>[...modeForms,firstMode]);
            const arr:any=[...modeRecords];
            arr.push(modeRec);
            setModeRecords(arr);
            var tasks=[...modeRecordsTaskRef.current];
            tasks.push(task);
            modeRecordsTaskRef.current=tasks;
            console.log(modeRecordsTaskRef.current);
        }else{
            const existFormArr=[];
            existFormArr.push(firstMode);
            setModeForms((prev:any)=>[firstMode]);
            const arr:any=[];
            arr.push(modeRec);
            setModeRecords((prev:any)=>arr);
            renderFunctionForm();
            modeRecordsTaskRef.current=[task];
        }
    }
    // update mode record 
    const modeOnChange=(index:number,name:string,value:any)=>{
        const update=modeRecords.map((rec:any,i)=>i==index
        ?{...rec,[name]:value}:rec);
        setModeRecords(update);
    }
    // update command task
    const commandOnChange=(modeIndex:number,name:string,subName:string="",value:any,arrayIndex:number=-1)=>{
        const functionRecs=[...modeRecordsTaskRef.current];
        const rec={...functionRecs[modeIndex]};
        if(name!=""){
            if(subName!=""){
                const recArr=[...rec[name]];
                if(arrayIndex>-1){
                    const arrRec={...recArr[arrayIndex]};
                    arrRec[subName]=value;
                    recArr[arrayIndex]=arrRec;
                    rec[name]=[...recArr];
                }
            }else{
                rec[name]=value;
            }
        }
        modeRecordsTaskRef.current[modeIndex]=rec;
    }
    const pinOnChange=(name:string,subName:string="",value:number,arrayIndex:number=-1)=>{
        const rec=[...modeRecordsTaskRef.current];
        const firstRec={...rec[0]};
        const pins=[...firstRec[name]];
        const pinRec={...pins[arrayIndex]};
        pinRec[subName]=value;
        pins[arrayIndex]=pinRec;
        firstRec[name]=pins;
        pinsUsedRef.current=pins;
        for(var i=0; i<rec.length; i++){
            modeRecordsTaskRef.current[i][name]=pins;
        }
    }
    const removeMode=(index:number)=>{
        var prev=index--;
        debugger;
        //var tabs=modeTabsRef.current.onSelectTab(prev);
        const arrRec=[...modeRecords];
        const arrForm=[...modeForms];
        arrRec.splice(index,1);
        arrForm.splice(index,1);
        setModeRecords(arrRec);
        //setModeForms(arrForm);
    }
    const renderModeForm=(array:Array<any>,index:number)=>{
        return array;
    }
    const deleteModeDisabled=()=>{
        var res=false;
        if(modeForms.length<2) res=true;
        return res;
    }
    const submit=async()=>{
        // combined function and modes
        var functionRec=functionRecord;
        var modesRec=modeRecords;
        var modeTasksRec=modeRecordsTaskRef.current;
        for(var i=0; i<modesRec.length; i++){
            modesRec[i].boardAction=modeTasksRec[i];
        }
        functionRec.mode=modesRec;
        functionRec.command=command;
        const deviceId=deviceRef.current.deviceId;
        // post to API
        debugger;
        const request=await base.util.fetchClient('/function/'+deviceId,'POST',functionRec);
        const status=await request?.status;
        if(status==200&&request.ok){
            router.push('/boards/'+boardRef.current.boardId);
        }
        console.log(functionRec);
        console.log(modeRecords);
        console.log(modeRecordsTaskRef.current);
    }
    const loadData=(commands:Array<any>,hardware:any,board:any,device:any,functionLayout:Object,modeLayout:Object)=>{
        setCommands(commands);
        hardwareRef.current=hardware;
        boardRef.current=board;
        deviceRef.current=device;
        setFunctionRecord(functionLayout);
        modeLayoutRef.current=modeLayout;
    }
    useEffect(()=>{
        console.log(data);
        loadData(data.commands,data.board.hardware,data.board.board,data.device,data.newFunction,data.newMode);
    },[commands,boardRef,deviceRef]);
    return(
        <div>
        <Row>
        <Col md={2}>
        <BackButton url={"/boards/"+boardRef.current?.boardId}/>
        </Col>
        <Col xs={9}>
        <FormHandle recordLayout={data.newFunction} ref={formRef} onSubmit={submit}>
        <TabGroup ref={tabsRef} defaultActiveKey={"command"}>
        <TabComponent title={"Function"} eventKey={"command"}>
        <Row>
        <Col md={4}>
        <FormGenText required={true} label={"Function Name"} name="route" type={""} rows={0} value={functionRecord?.route} onChange={(event:any)=>base.onChange(event.target.name,event.target.value,setFunctionRecord,functionRecord)}/>
        <FormGenSelect label={"Command"} name="command" valueKey={""} displayKey={"command"} value={undefined} options={data.commands} onChange={(event)=>commandHandle(event)} required={true}/>
        </Col>
        <Col md={4}>
        {
            functionForm
        }
        </Col>
        </Row>
        <Row>
        <Col md={2}>
        {!addMode?
            <RegularButton caption={"Save Function"} size={undefined} type={undefined} disabled={command==null}/>
        :null}
        {addMode?
        <RegularButton caption={"Next"} size={undefined} type={'button'} onClick={()=>tabHandle("mode")}/>
        :null}
        </Col>
        </Row>  
        </TabComponent>
        {addMode?
            <TabComponent  title={"Mode"} eventKey={"mode"}>
            <TabGroup defaultActiveKey={0} ref={modeTabsRef}>
            {
                modeForms.map((arr:any,index:number)=>(
                    <TabComponent title={modeRecords[index].mode} eventKey={index}>
                    <Row>
                    <Col md={4} xs={8}>
                    <FormGenText label={"Mode"} type={""} rows={0} name={'mode'} value={modeRecords[index].mode} onChange={(event:any)=>modeOnChange(index,event.target.name,event.target.value)} />
                    {
                        renderModeForm(arr,index)
                    }
                    </Col>
                    </Row>
                    <Row>
                    <Col>
                    <RegularButton caption={"Back"} size={undefined} type={"button"} onClick={()=>tabHandle("command")}/>
                    <RegularButton caption={"Save Function"} size={undefined} type={"submit"}/>
                    <RegularButton caption={"Delete"} size={undefined} type={"button"} onClick={()=>removeMode(index)} disabled={false}/>
                    <RegularButton caption={"Add Mode"} size={undefined} type={"button"} onClick={()=>addModeForm()}/>
                    </Col>
                    </Row>   
                    </TabComponent>
                ))
                
            }
            </TabGroup>    
            </TabComponent>
        :null}
        </TabGroup>
        </FormHandle>
        </Col>
        </Row>
        </div>
    );
}