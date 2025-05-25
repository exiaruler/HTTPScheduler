import PageGroup from "@/app/next-components/pageGroup";
import Client from "./client";
import { NextBase } from "@/NextBase";
async function getBoard(id:any) {
  var dataResp={
    board:null,
    deviceForm:null
  };
  const base=new NextBase();
  var boards=await base.fetchClientGet('/board/uni-board/'+id);
  var form=await base.fetchClientGet('form/board');
  dataResp.board=boards;
  dataResp.deviceForm=form;
  return dataResp;
}
export default async function Page({params}:any){
    var param=await params?.id;
    var board=await getBoard(param);
    return (
        <PageGroup url={'/board/'}>
        <Client data={board}/>
        </PageGroup>
    )
}