import Util from "./base/Util";
interface GetInput{
    api:string,
    key:string
}
// override class
export class NextBase extends Util{
    // override origin
    //originUrl=this.getOrigin();
    // override encrypt key in next.js
    encryptKey=process.env.NEXT_PUBLIC_API_ENCRYPTKEY||'';
    // application URL
    baseURL="http://localhost:3000";
    // IoT URL
    baseUrlIo="http://localhost:8080";
    /*
    public getOrigin(){
        var url='http://localhost:3000';
        return url;
    }
        */
    public getOriginUrl(){
      var url=process.env.NEXT_PUBLIC_API_DOMAIN||'http://localhost:3000';
      return url;
    }
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
    public async fetchClient(url:string,method:string,body:any){
        var request=null;
        const payload=JSON.stringify(body);
        const config=this.apiCallConfig(method,payload);
        try{
            request=await fetch(this.baseURL+'/api'+url,config);
            
        }catch(err){

        }
        return request;
    }
    public async fetchGetApi(input:Array<GetInput>){
        var result={};
        for(var i=0; i<input.length; i++){
            var req=input[i];
            const request=await this.fetchClientGet(req.api);
            var key=req.key;
            result={...result,[req.key]:request};
        }
        return result;
    }

}