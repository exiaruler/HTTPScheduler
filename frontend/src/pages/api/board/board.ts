import { NextApiRequest, NextApiResponse } from "next";
import { NextUtil } from "@/pages/NextUtil";
const base=new NextUtil();
export default async function handler(req: NextApiRequest, res: NextApiResponse) {
    var response=base.responseFormat();
    if(req.method=='GET'){
        const request=await base.fetchRequest('/board/getboards','GET',null,base.baseUrlIo);
        if(request.ok){
            var boards=await request.json();
            boards.push({id:0,name:'Add Board'});
            response.data=boards;
        }
    }
    if(req.method=='POST'){
        var body=req.body;
        const request=await base.fetchRequest('/board/add-board-socket','Post',body,base.baseUrlIo);
        response.status=await request.status;
        if(request.ok){

        }
        
    }
    res.status(response.status).json(response.data);
}