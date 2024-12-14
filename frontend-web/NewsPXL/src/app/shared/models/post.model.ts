export class Post{
    id?: number;
    title: string;
    content: string;
    createdAt:string;
    status: string;
    author: string;

    constructor(title: string, content: string, createdAt:string, status: string, author: string){
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.status = status;
        this.author = author;


    }
       

}