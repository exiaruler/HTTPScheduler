import { NextApiRequest, NextApiResponse } from "next";
import { NextUtil } from "@/pages/NextUtil";
const base=new NextUtil();
export default async function handler(req: NextApiRequest, res: NextApiResponse) {
    var response=base.responseFormat();
    if(req.method=='GET'){
        const request=await base.fetchRequest('/board/getboards','GET',null,base.baseUrlIo);
        if(request.ok){
            response.data=await request.json();
        }
    }
    if(req.method=='POST'){
        
    }
    res.status(response.status).json(response.data);
}