export interface Post {
    id?: number;
    title: string;
    content: string;
    author: string;
    status: 'DRAFT' | 'PUBLISHED';
    createdAt?: string;
  }
  