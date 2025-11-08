export interface ApiResponse<T> {
  timestamp: Date;
  status: number;
  data?: T;
  error?: T;
}
