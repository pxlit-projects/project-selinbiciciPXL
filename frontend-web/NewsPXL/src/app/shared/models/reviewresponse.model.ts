
export interface ReviewResponse{
    id: number;
    postId: number;
    content: string;
    author: string;
    createdDate: Date;
    statusType: string;
}
