export interface Review {
    id: number;
    postId: number;
    author: string;
    content: string;
    statusReview: string | null;
    createdDate: string;
    
  }