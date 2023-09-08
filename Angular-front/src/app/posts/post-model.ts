import { ReactType } from './react-type';

export interface Post {
  id: number;
  content: string;
  creationDate: Date;
  username: string;
  heartCount: number;
  likeCount: number;
  dislikeCount: number;
  checked: ReactType;
}
