import { Post } from "./post.model";


export interface PostResponse extends Post {
    id: number;
    title: string;
    content: string;
    author: string;
    createdDate: string;
    status: string;
  }