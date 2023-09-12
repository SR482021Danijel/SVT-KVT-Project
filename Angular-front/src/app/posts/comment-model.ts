import { ReactType } from './react-type';

export interface Comment {
  id: number;
  text: string;
  timestamp: Date;
  username: string;
  heartCount: number;
  likeCount: number;
  dislikeCount: number;
  checked: ReactType;
}
