export interface Review {
    id: number;
    postId: number;
    content: string;
    author: string;
    statusType: string;
    createdAt: Date;
  }