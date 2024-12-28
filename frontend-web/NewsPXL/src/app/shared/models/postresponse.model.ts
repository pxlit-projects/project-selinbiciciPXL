import { Post } from "./post.model";
import { Review } from "./review.model";


export interface PostResponse extends Post {
    id: number;
    title: string;
    content: string;
    author: string;
    createdDate: string;
    status: string;
    reviews: Review[];
  }