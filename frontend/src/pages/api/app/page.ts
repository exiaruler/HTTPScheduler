import type { NextApiRequest, NextApiResponse } from 'next'
import { NextBase } from '@/NextBase';
const util=new NextBase();
export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  var pages=[];
  if(req.method=='GET'){
    const request=await util.fetchRequest("/page/get-app-pages/"+'iot-hub');
    if(!request.ok){
        throw new Error('error happened');
    }
    pages=await request.json();
  }
  var encrypt=util.encryptValue(JSON.stringify(pages));
  res.status(200).json(encrypt);
}