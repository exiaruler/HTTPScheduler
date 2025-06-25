import FormGenSelect from "@/components/formGenComponents/FormGenSelect";
import { Col, Form, Row } from "react-bootstrap";

export default class ElectrodeSelect extends FormGenSelect{
    
    render(){
        return(
            <Row>
            <Col md={this.props.md} xs={this.props.xs}>
            <Form.Group>
            <Form.Label>{"Electrode"}</Form.Label>
            <div className="mb-3">
            <Form.Select disabled={this.props.disable} style={{width:this.props.size}} id={this.props.name+"Text"} required={this.props.required} onChange={(event:any)=>this.onChange(event)}  name={this.props.name} value={this.props.value}>
            {
                <option hidden={false}>{''}</option>
            }
            {
                this.state.options.map((opt:any,num:number)=>(
                    <option value={opt}>{opt}</option>
                ))
            }
            </Form.Select>
            </div>
            <Form.Text id={this.props.name+"Warning"} >{this.props.warning} </Form.Text>
            </Form.Group>
            </Col>
            </Row>
        );
    }
}