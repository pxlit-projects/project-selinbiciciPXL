
export class Post{
    id?: number;
    title: string;
    content: string;
    createdDate: string;
    status: string;
    author: string;
   
    

    constructor(title: string, content: string, createdDate:string, status: string, author: string){
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.status = status;
        this.author = author;


    }
       

}