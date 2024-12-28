
export interface ReviewResponse{
    id: number;
    postId: number;
    content: string;
    author: string;
    createdDate: Date;
    statusReview: string;
    userRole: string | null;
}
