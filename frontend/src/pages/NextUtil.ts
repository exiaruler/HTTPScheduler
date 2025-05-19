import { NextBase } from "@/NextBase";
interface ResponseFormat{
    status:number;
    data:any;
}
export class NextUtil extends NextBase{
    
    private jsonTransform(data:any){
        return { ...data, source: 'proxied-through-nextjs' };
    }
    public returnResponse(data:any){
        const transformedData=this.jsonTransform(data);
        return new Response(JSON.stringify(transformedData),
    {
        headers: { 'Content-Type': 'application/json' }
    });
    }
    public responseFormat(){
        var response:ResponseFormat={
            status: 200,
            data: null
        }
        return response;
    }
}