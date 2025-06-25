import PageGroup from "@/app/next-components/pageGroup";
import Client from "./client";
import { NextBase } from "@/NextBase";
async function getData(boardId:string,deviceId:string) {
  var dataResp={
    board:null,
    device:null,
    commands:[],
    electrodes:[],
    newFunction:null,
    newMode:null
  };
  const base=new NextBase();
  var fetchApis:any=await base.fetchGetApi([{api:'/board/uni-board/'+boardId,key:'board'},
    {api:'/command/command',key:'command'},
    {api:'/command/core/core',key:'electrodes'},
    {api:'/function/new',key:'newFunction'},
    {api: "/function/mode/new",key: "newMode"}
  ]);
  dataResp.board=fetchApis.board;
  dataResp.commands=fetchApis.command;
  dataResp.device=fetchApis.board.board.device.find((dev:any)=>dev.deviceId===deviceId);
  dataResp.electrodes=fetchApis.electrodes;
  dataResp.newFunction=fetchApis.newFunction;
  dataResp.newMode=fetchApis.newMode;
  return dataResp;
}
export default async function page({params}:any){
    const { board,device } = await params;
    var data=await getData(board,device);
    return(
        <PageGroup url="/device/function/">
        <Client data={data}/>
        </PageGroup>
    );
}