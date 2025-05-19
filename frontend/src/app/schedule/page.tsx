import { Col, Row } from "react-bootstrap";
import TableComponent from "../next-components/TableComponent";
import TableComponentColumn from "@/components/Table/TableComponentColumn";
import PageGroup from "../next-components/pageGroup";


export default function page(){
    return(
        <PageGroup>
        <Row>
        <Col md={2}></Col>
        <Col md={9}>
        <TableComponent results={[]} idKey={"id"}>
        <TableComponentColumn key={"name"} columnName={"Name"}/>
        <TableComponentColumn key={"time"} columnName={"Schedule Time"}/>
        </TableComponent>
        </Col>
        <Col md={4}></Col>
        </Row>
        </PageGroup>
    );
}