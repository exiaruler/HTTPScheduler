import Util from "./base/Util";
// override class
export class NextBase extends Util{
    // override encrypt key in next.js
    encryptKey=process.env.NEXT_PUBLIC_API_ENCRYPTKEY||'';
    // application URL
    baseURL="http://localhost:3000";
    // IoT URL
    baseUrlIo="http://localhost:8080";

    public async fetchClientGet(url:string){
        var response=null;
        try{
            const request=await fetch(this.baseURL+'/api'+url,this.apiCallConfig('GET'));
            if(request.ok){
                response=await request.json();
            }
        }catch(err){

        }
        return response;
    }
}