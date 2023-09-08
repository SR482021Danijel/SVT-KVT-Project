import { Post } from './post-model';
import { ReactType } from './react-type';

export interface Reaction {
  id?: number;
  reaction: ReactType;
  timeStamp: Date;
  post: Post;
  username: string;
}
