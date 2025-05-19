'use client'
import { forwardRef, useImperativeHandle, useState } from "react"
import { Modal, ModalBody, ModalFooter, ModalHeader } from "react-bootstrap";
import { ButtonComponent } from "../Buttons/ButtonComponent";
type modalContent={
    title:string,
    children?:any,
    buttonChildren?:any,
    submitCaption?:string,
    hideSubmit?:boolean,
    submit?:any,
    onClose?:any,
}
// modal box
const ModalBox=forwardRef(function ModalBox(props:modalContent,ref){
    const [show,setShow]=useState(false);
    const [showSubmit,setShowSubmit]=useState(false);
    var saveCaption='Save';

    const open=()=>{
        setShow(true);
    }
    const close=()=>{
        if(props.onClose){
            props.onClose();
        }
        setShow(false);
    }
    const submitHandle=async(event:any)=>{
        event.preventDefault();
        if(props.submit){
            props.submit();
        }
    }
    if(props.submitCaption){
        saveCaption=props.submitCaption;
    }
    useImperativeHandle(ref,()=>{
        return {
            open,
            close
        }
    },[]);
    return (
        <Modal show={show} onHide={close}>
        <form onSubmit={submitHandle}>
        <ModalHeader>
        {props.title}
        </ModalHeader>
        <ModalBody>
        {
            props.children
        }
        </ModalBody>
        <ModalFooter>
        {
            props.buttonChildren
        }
        <ButtonComponent caption={"Close"} size={undefined} type={'button'} onClick={close}/>
        {!showSubmit?
        <ButtonComponent caption={saveCaption} size={undefined} type={'submit'}/>
        :null}
        </ModalFooter>
        </form>
        </Modal>
    )
});
export default ModalBox;