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
            const request=await base.fetchRequest('/mode/new-record','GET',null,base.baseUrlIo);
            if(request.ok){
                response.data=await request.json();
                console.log(response);
            }else response.status=404;
        }
    }
    res.status(response.status).json(response.data);
}