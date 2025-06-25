import { useRouter } from "next/navigation";
import { RegularButton } from "./RegularButton";
interface Props{
    url:string;
}
export default function BackButton(props:Props){
    const router = useRouter();
    const handleClick=()=>{
        router.push(props.url);
    }
    return(
        <RegularButton caption={"Back"} size={undefined} type={undefined} onClick={handleClick}/>
    )
}