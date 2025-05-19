'use client'
import Link from "next/link";
import { Card, CardBody, CardTitle, Col, Row } from "react-bootstrap"
interface Props{
    data:Array<Object>;
    valueKey:string;
    titleKey:string;
    bodyKey:string;
    enableLink?:boolean;
    linkUrl?:any;
    onClick?:any;
    mdCol?:number;
    smCol?:number;
}
export default function GridCard(props:Props){
    var valueKey="";
    var disableLink='disabled-link';
    var link='';
    if(props.linkUrl&&props.linkUrl!=""){
        link=props.linkUrl;
    }
    if(props.valueKey){
        valueKey=props.valueKey;
    }
    if(props.enableLink){
        disableLink='';
    }
    const createLink=(obj:any)=>{
        var url=link+obj[valueKey];
        return url;
    }
    const onClick=(event:any)=>{
        if(props.onClick){
            props.onClick(event);
        }
    }
    return(
        <div>
        <Row>
        {
            props.data.map((obj:any,num:number)=>(
                <Col sm={props.smCol} md={props.mdCol}key={obj[valueKey]||num}>
                <Link href={createLink(obj)} className={disableLink} onClick={(event:any)=>onClick(event)}>
                <Card>
                <CardTitle>{obj[props.titleKey]}</CardTitle>
                <CardBody>{obj[props.bodyKey]}</CardBody>
                </Card>
                </Link>
                </Col>
            ))
        }
        </Row>
        </div>
    )
}