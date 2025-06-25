import { NextApiRequest, NextApiResponse } from "next";
import { NextUtil } from "@/pages/NextUtil";
const base=new NextUtil();
// retrieve form
export default async function handler(req: NextApiRequest, res: NextApiResponse) {
    var response=base.responseFormat();
    if(req.method=='GET'){
        var param=req.query.id;
        console.log(param)
        if(param=="new"){
            const request=await base.fetchRequest('/route/new-record','GET',null,base.baseUrlIo);
            if(request.ok){
                response.data=await request.json();
            }else response.status=404;
        }
    }
    if(req.method=='POST'){
        const deviceId=req.query.id;
        const content=req.body;
        const request=await base.fetchRequest('/route/add-route-socket/'+deviceId,'POST',content,base.baseUrlIo);
        if(request.ok){
            response.data=await request.json();
        }else response.status=404;
    }
    res.status(response.status).json(response.data);
}